# !/bin/bash
echo '++++++++++++ start start.sh date++++++++++++'
rm -rf logs
mkdir logs
echo start eureka
nohup java -jar eureka-*.jar >logs/eureka.nohup.log 2>&1 &
sleep 5;
names=`ls | grep .jar| grep -v eureka|grep -v grep`

for name in $names
do
  echo "start  $name!"
  nohup java -jar $name >logs/$name.nohup.log 2>&1 &
done
echo '++++++++++++ en start.sh date++++++++++++'