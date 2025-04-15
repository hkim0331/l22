dev:
	lein repl

uberjar:
	lein uberjar

DEST:='ubuntu@l22.melt.kyutech.ac.jp'
deploy: uberjar
	scp target/uberjar/l22.jar {{DEST}}:l22/ && \
	ssh {{DEST}} 'sudo systemctl restart l22' && \
	ssh {{DEST}} 'systemctl status l22'

clean:
	rm -r target
