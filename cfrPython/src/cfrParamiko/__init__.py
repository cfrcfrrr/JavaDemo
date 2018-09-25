#-*-coding:utf-8-*-

import paramiko
from sys import stdout, stderr

if __name__ == '__main__':
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect('192.168.18.129', 22, 'chenfurong', 'cfrcfrrr')
    stdin, stdout, stderr = ssh.exec_command('/sbin/ifconfig')
    print(stdout.readlines())
    ssh.close()