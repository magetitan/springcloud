echo '++++++++++++ start copy.sh date++++++++++++'
git pull
mvn clean install -Dmaven.test.skip=true
targets=`find ./ -name target`
rm -rf jars
mkdir jars
for target in $targets
do
    cp -f $target/*.jar ./jars/
done
cp application.properties ./jars/
cp stop.sh ./jars/
cp start.sh ./jars/


echo '++++++++++++ start copy.sh date++++++++++++'