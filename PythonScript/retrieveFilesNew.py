import filecmp
import os

# Determine the items that exist in both directories
path1 = '/Users/rshaika/Desktop/test1'
path2 = '/Users/rshaika/Desktop/test2'
d1_contents = set(os.listdir(path1))
d2_contents = set(os.listdir(path2))
common = list(d1_contents & d2_contents)
common_files = [
    f
    for f in common
    if os.path.isfile(os.path.join(path1, f))
]
print('Common files:', common_files)

# Compare the directories
match, mismatch, errors = filecmp.cmpfiles(
    path1,
    path2,
    common_files,
)
print('Match       :', match)
print('Mismatch    :', mismatch)
print('Errors      :', errors)