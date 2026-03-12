echo "$PLUGIN_CLOUD_RUN_SERVICE_MANIFEST_FILE_CONTENT" > values.yaml
echo "Rendering Values from Helm Chart"
curl https://<chart-location> # Fetch helm chart, in path $PWD/chart
curl https://<helm-cli> -O # Fetch helm cli
chmod +x helm
helm template cloud-run chart/ > rendered.yaml
export PLUGIN_CLOUD_RUN_SERVICE_MANIFEST_FILE_CONTENT=$(cat rendered.yaml)
/opt/harness/bin/harness-cloud-run-plugin
exit 0
