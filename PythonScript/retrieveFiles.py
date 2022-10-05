# Import Module
import multiprocessing
import os

# Folder Path
path = "/Users/rshaika/Desktop/"
# Change the directory
path2 = "/Users/rshaika/Documents/"
os.chdir(path)

# Read text File

def read_text_file(file_path):
    with open(file_path, 'r') as f:
        print(f.read())


# iterate through all file
for file in os.listdir():
    # Check whether file is in text format or not
    if file.endswith(".txt"):
        file_path = f"{path}/{file}"

        # call read text file function
        read_text_file(file_path)

def list_files_in_path(filepath):
    os.chdir(filepath)
    for file in os.listdir():
    # Check whether file is in text format or not
        if file.endswith(".txt"):
            file_path = f"{filepath}/{file}"

            # call read text file function
            read_text_file(file_path)
# Sequential processing
list_files_in_path(path)
list_files_in_path(path2)

#Attempted multi processing
process1 = multiprocessing.Process(target=list_files_in_path(path))
process2 = multiprocessing.Process(target=list_files_in_path(path2))
process1.start()
process2.start()
process1.join()
process2.join()
processes =[]
for _ in range(10):
    p = multiprocessing.Process(target=list_files_in_path, args=[path])
    p.start()
    processes.append(p)

for process in processes:
    process.join()

with concurrent.futures.ProcessPoolExecutor() as executor:
    f1 =executor.submit(list_files_in_path, path)
    f2 = executor.submit(list_files_in_path, path2)