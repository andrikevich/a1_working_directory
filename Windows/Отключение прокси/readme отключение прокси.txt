��� ��������������� ���������� PROXY:
1. �������� ���� 444.reg � ����� Windows\System32
2. ������� ���� Proxi_off.cmd

   @echo off 
regedit.exe /s 444.reg 
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Internet Settings" /v AutoConfigURL /t REG_SZ /d "" /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings" /v ProxyEnable /t REG_DWORD /d 0 /f 

3. ������� ����� �� ������� ���� ��� Proxi_off.cmd
4. ������ ������---> ��������-->������� �����---> ctrl+alt+z
