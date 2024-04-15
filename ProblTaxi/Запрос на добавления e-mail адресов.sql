select w.City, w.problem,  count(*) as Kolichestvo, w.mail
from
(SELECT IIf ((RAT="WCDMA"or RAT="GSM+WCDMA") and city="Gomel" or (RAT="WCDMA"or RAT="GSM+WCDMA") and city="Grodno" or (RAT="WCDMA"or RAT="GSM+WCDMA") and city="Vitebsk","p.konik@velcom.by",(iif ((RAT="WCDMA"or RAT="GSM+WCDMA") and city="Minsk" or RAT="WCDMA" and city="Mogilev" or (RAT="WCDMA"or RAT="GSM+WCDMA") and city="Bobrujsk","a.grin@velcom.by",(Iif (RAT="GSM" and city="Bobrujsk" or RAT="GSM" and city="Gomel" or RAT="GSM" and city="Grodno","s.pisarik@velcom.by", (iif (RAT="GSM" and city="Brest" or (RAT="WCDMA"or RAT="GSM+WCDMA") and city="Brest","s.smoljak@velcom.by", (iif (RAT="GSM" and city="Vitebsk" or RAT="GSM" and city="Mogilev","v.dubovik@velcom.by",(iif (RAT="GSM" and city="Minsk","a.prashkovich@velcom.by"))))))))))) as mail, *
FROM Main) w

where w.status="Open"
group by w.city, w.problem, w.mail;
