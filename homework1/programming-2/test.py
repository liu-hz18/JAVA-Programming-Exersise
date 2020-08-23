
import random
from tqdm import tqdm
import os
import filecmp

niter = 1000


def ok_sudoku(n):
    m = n*n
    with open('temp.in', 'w', encoding='utf-8') as fin:
        fin.write(str(n) + '\n')
        for i in range(m):
            for j in range(m):
                fin.write(str(((i % n) * n + i // n + j) % m + 1) + ' ')
            fin.write('\n')
    with open('temp.ans', 'w', encoding='utf-8') as fin:
        fin.write('yes\n')


def random_sudoku(n):
    m = n*n
    number_arr = list(range(1, m+1))
    with open('temp.in', 'w', encoding='utf-8') as fin:
        fin.write(str(n) + '\n')
        for i in range(m):
            random.shuffle(number_arr)
            for num in number_arr:
                fin.write(str(num) + ' ')
            fin.write('\n')
    with open('temp.ans', 'w', encoding='utf-8') as fin:
        fin.write('no\n')

'''
for i in tqdm(range(1, 33)):
    ok_sudoku(i)
    if 0 != os.system('java Main < temp.in > temp.out'):
        print('Runtime Error!')
        exit(1)
    if not filecmp.cmp('temp.ans', 'temp.out'):
        print('ERROR!')
        exit(1)
'''

for i in tqdm(range(niter)):
    random_sudoku(random.randint(2, 2))
    if 0 != os.system('java Main < temp.in > temp.out'):
        print('Runtime Error!')
        exit(1)
    if not filecmp.cmp('temp.ans', 'temp.out'):
        print('ERROR!')
        exit(1)
