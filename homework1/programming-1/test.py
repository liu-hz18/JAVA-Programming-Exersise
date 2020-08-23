import os
import tqdm
import random
import filecmp

niter = 1000

bit1 = 5000
bit2 = 5000

for i in tqdm.tqdm(range(niter), desc='checking...OK'):
    number1 = random.randint(0, pow(10, random.randint(0, bit1)))
    number2 = random.randint(0, pow(10, random.randint(0, bit2)))
    with open('temp.in', 'w', encoding='utf-8') as fin:
        fin.write(str(number1) + ' ' + str(number2) + '\n')
    with open('temp.ans', 'w', encoding='utf-8') as fin:
        fin.write(str(number1 + number2) + '\n')
    if 0 != os.system('java Main < temp.in > temp.out'):
        print('Runtime Error!')
        exit(1)
    if not filecmp.cmp('temp.ans', 'temp.out'):
        print('ERROR!')
        exit(1)
