# !/bash/bin
echo '++++++++++++ start stop.sh date++++++++++++'
names=`ls | grep .jar`

for name in $names;
do
  echo "stop $name!";
  jarname=`ps x | grep $name |grep -v grep | awk '{print $1}' | xargs echo`;
  if [ -n "$jarname" ];then
	echo "kill -9 "$jarname"!"
    	ps x | grep $name |grep -v grep | awk '{print $1}' | xargs kill -9;
  fi
done
echo '++++++++++++ end stop.sh date++++++++++++'