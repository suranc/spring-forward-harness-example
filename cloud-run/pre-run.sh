export PLUGIN_CLOUD_RUN_SERVICE_MANIFEST_FILE_CONTENT=$(echo $PLUGIN_CLOUD_RUN_SERVICE_MANIFEST_FILE_CONTENT|sed 's/value/newvalue/')
/opt/harness/bin/harness-cloud-run-plugin
exit 0
