apiVersion: serving.knative.dev/v1
kind: Service
metadata:
  annotations:
    run.googleapis.com/ingress: all
    run.googleapis.com/ingress-status: all
  labels:
    cloud.googleapis.com/location: us-central1
  name: {{ .Values.serviceName }}
spec:
  template:
    metadata:
      annotations:
        autoscaling.knative.dev/maxScale: '100'
        run.googleapis.com/client-name: gcloud
        run.googleapis.com/client-version: 457.0.0
        run.googleapis.com/startup-cpu-boost: 'true'
      labels:
        run.googleapis.com/startupProbeType: Default
    spec:
      containerConcurrency: 80
      containers:
      - image: {{ .Values.imageName }}
        name: {{ .Values.serviceName }}-1
        ports:
        - containerPort: 8080
          name: http1
        resources:
          limits:
            cpu: 1000m
            memory: 512Mi
        startupProbe:
          failureThreshold: 1
          periodSeconds: 240
          tcpSocket:
            port: 8080
          timeoutSeconds: 240
      serviceAccountName: 980596850008-compute@developer.gserviceaccount.com
      timeoutSeconds: 300
  traffic:
  - latestRevision: true
    percent: 0
  - revisionName: {{ .Values.lastRevision }}
    percent: 100
    