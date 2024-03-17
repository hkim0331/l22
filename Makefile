DEST=ubuntu@l22.melt.kyutech.ac.jp
TAG=hkim0331/l22:0.1

all: deploy

build:
	docker build -t ${TAG} .

uberjar:
	lein uberjar

deploy: uberjar
	scp target/uberjar/l22.jar ${DEST}:l22/ && \
	ssh ${DEST} 'sudo systemctl restart l22' && \
	ssh ${DEST} 'systemctl status l22'

clean:
	${RM} -r target
