cd ~/.ssh
echo "SendEnv MYSQL_USERSERVICE_DB" >> tempconfig
echo "SendEnv MYSQL_USERSERVICE_USER" >> tempconfig
echo "SendEnv MYSQL_USERSERVICE_PASSWORD" >> tempconfig
echo "SendEnv SPRING_PROFILES_ACTIVE" >> tempconfig
value=$(<config)
echo "$value" >> tempconfig
cp tempconfig config
cat tempconfig
rm tempconfig