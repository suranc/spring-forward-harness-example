FROM alpine

ENV HOSTNAME localhost

RUN apk add curl

CMD curl http://$HOSTNAME