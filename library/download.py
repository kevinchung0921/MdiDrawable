#!/usr/bin/env python
import os
import sys
import requests
from zipfile import ZipFile
import re
from shutil import copyfile


WORK_DIR = './mdi/'
MDI_URL = 'https://github.com/Templarian/MaterialDesign-Webfont/archive/master.zip'
ZIP_FILE = WORK_DIR+'mdi.zip'

CSS_FILE = WORK_DIR+'MaterialDesign-Webfont-master/css/materialdesignicons.css'
STRING_TABLE = './src/main/res/values/mdi_strings.xml'
FONT_FOLDER = './src/main/res/font/'

FONT_FILE = WORK_DIR+'MaterialDesign-Webfont-master/fonts/materialdesignicons-webfont.ttf'

MDI_PREFIX_PATTERN = r'^\.(mdi-[a-zA-Z0-9\-]+)::before'
MDI_CONTENT_PATTERN = r'\s+content:\s+"\\(\w+)"'

# create folder for download material deisgn icon package
if(not os.path.isdir(WORK_DIR)):
	print('create folder:'+WORK_DIR)
	os.mkdir(WORK_DIR)

# download package if not exists
if(not os.path.isfile(ZIP_FILE)):
	print('download material design icon package...')
	open(ZIP_FILE,'wb').write(requests.get(MDI_URL, allow_redirects=True).content)

# unzip file
zf = ZipFile(ZIP_FILE, 'r')
zf.extractall(WORK_DIR)
zf.close()

# check if asset folder created
if(not os.path.isdir(FONT_FOLDER)):
	os.mkdir(FONT_FOLDER)

# check if font file copied
if(not os.path.isfile(FONT_FOLDER+FONT_FILE)):
	copyfile(FONT_FILE,FONT_FOLDER+'/mdifont.ttf')

if(os.path.isfile(CSS_FILE)):
	print('found css file, generating string table ..')
	prefixFound = False  # True wheb required prefix found

	lines = open(CSS_FILE).readlines()
	stringTable = open(STRING_TABLE,"w")
	outputLines = ['<?xml version="1.0" encoding="utf-8"?>\n<resources>\n']

	prefixRe = re.compile(MDI_PREFIX_PATTERN)
	contentRe = re.compile(MDI_CONTENT_PATTERN)
	outputline = ""
	count = 0
	for line in lines:
		if(not prefixFound):
			match = prefixRe.match(line)
			if(match):
				name = match.group(1).replace('-','_')
				outputline = '<string name="'+name+'">'
				print('<item>'+name.replace('mdi_','')+'</item>')
				prefixFound = True
		else:
			match = contentRe.match(line)
			if(match):
				content = match.group(1)
				if(not content.startswith('FFF')):
					outputline +='&#x'+content+';</string>\n'
					outputLines.append(outputline)
					count = count+1
			outputline = ""
			prefixFound = False

	outputLines.append('</resources>')
	outfile = open(STRING_TABLE, 'w')
	outfile.writelines(outputLines)
	outfile.close()
	print('total '+str(count)+' entries')
	print('DONE')
else:
	print('Can not found CSS file, abort ..')
