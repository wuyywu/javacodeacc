//package com.github.commonutils.rabbitmq.rpc;
//
//import com.rabbitmq.client.*;
//
//import java.io.IOException;
//import java.util.UUID;
//import java.util.concurrent.TimeoutException;
//
///**
// * https://www.cnblogs.com/LipeiNet/p/5980802.html
// *
// * @author wuyun
// * @date 2019/1/2 20:52
// */
//public class RPCClient {
//    private Connection connection;
//    private Channel channel;
//    private String requestQueueName = "rpc_queue";
//    private String replyQueueName;
//    private QueueingConsumer consumer;
//
//    public static void main(String[] args) throws Exception {
//        RPCClient rpcClient = null;
//        String response;
//        try {
//            rpcClient = new RPCClient();
//            System.out.println("RPCClient  Requesting fib(20)");
//            response = rpcClient.call("20");
//            System.out.println("RPCClient  Got '" + response + "'");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rpcClient != null) {
//                rpcClient.close();
//            }
//        }
//    }
//
//    public RPCClient() throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setUsername("wuyun");
//        factory.setPassword("wuyun");
//        factory.setPort(5672);
//        connection = factory.newConnection();
//        channel = connection.createChannel();
//
//        replyQueueName = channel.queueDeclare().getQueue();
//        consumer = new QueueingConsumer(channel);
//        channel.basicConsume(replyQueueName, true, consumer);
//    }
//
//    public String call(String message) throws IOException, InterruptedException {
//        String response;
//        String corrID = UUID.randomUUID().toString();
//        AMQP.BasicProperties props = new AMQP.BasicProperties().builder()
//                .correlationId(corrID).replyTo(replyQueueName).build();
//        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
//        while (true) {
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            if (delivery.getProperties().getCorrelationId().equals(corrID)) {
//                response = new String(delivery.getBody(), "UTF-8");
//                break;
//            }
//        }
//        return response;
//    }
//
//    public void close() throws Exception {
//        connection.close();
//    }
//
//
//}
