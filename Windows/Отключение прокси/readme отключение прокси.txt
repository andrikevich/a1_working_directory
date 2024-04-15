Для автоматического отключение PROXY:
1. копируем файл 444.reg в папку Windows\System32
2. создаем файл Proxi_off.cmd

   @echo off 
regedit.exe /s 444.reg 
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Internet Settings" /v AutoConfigURL /t REG_SZ /d "" /f
reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings" /v ProxyEnable /t REG_DWORD /d 0 /f 

3. Создаем ЯРЛЫК на рабочий стол для Proxi_off.cmd
4. Правой мышкой---> Свойства-->Быстрый вызов---> ctrl+alt+z
