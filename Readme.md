## Kafka 학습용 레포지토리


### 실행 환경 구성
- docker-compose.yml 설정
- kafka를 구동하기 위해서는 kafka뿐 아니라 zookeeper이 필요하다.


### 예행 테스트
- 토픽 생성 


    .bin/kafka-topics --create --topic [토픽명] --bootstrap-server localhost:9092
- 토픽 확인

  
    .bin/kafka-topics --describe --topic [토픽명] --bootstrap-server localhost:9092
- 이벤트 발행


    .bin/kafka-console-producer --topic [토픽명] --bootstrap-server localhost:9092
- 이벤트 소비


    .bin/kafka-console-consumer --topic [토픽명] --from-beginning --bootstrap-server localhost:9092


### 참고 
- https://projectreactor.io/docs/kafka/release/reference/#_reactive_api_for_kafka
- https://velog.io/@qlgks1/%EC%B9%B4%ED%94%84%EC%B9%B4%EC%99%80-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8
- https://www.baeldung.com/ops/kafka-docker-setup
- https://velog.io/@hyejinjeong9999/Docker%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4%EC%84%9C-kafka-%EB%A1%9C%EC%BB%AC-%EC%84%A4%EC%B9%98
- https://kafka.apache.org/quickstart
