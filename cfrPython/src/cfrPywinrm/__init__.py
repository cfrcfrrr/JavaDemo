#-*-coding:utf-8-*-

import winrm, sys

if __name__ == '__main__':
    #服务器需要设置密码，要不然好像会有问题，传的密码设置为“”，会出错
    s = winrm.Session('http://192.168.18.130:80/wsman', auth=("Administrator", "CFR2015moveon"))
#     cmdResult = s.run_cmd('ipconfig', ['/all'])
    cmdResult = s.run_cmd('arp', ['/a'])
    if cmdResult.status_code != 0:
        print("err.")
        sys.exit(1)
#     cmdResult.raise_for_status()
    print(cmdResult.std_out)