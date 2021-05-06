import os

path='.'
files= os.listdir(path)

for file1 in files:
    if file1.endswith("xsd"):
        print("File: " + file1)

