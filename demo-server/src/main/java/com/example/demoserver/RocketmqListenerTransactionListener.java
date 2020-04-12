package com.example.demoserver;

/*import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;*/


/*@Service
//tx-01-group应该保证和发送者一致
@RocketMQTransactionListener(txProducerGroup = "tx-01-group")
public class RocketmqListenerTransactionListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        //arg  此处的arg就是消息设置的arg (arg一般是业务数据)
        MessageHeaders headers = message.getHeaders();
        String TRANSACTION_ID = (String)headers.get(RocketMQHeaders.TRANSACTION_ID);
        String data_id = (String)headers.get("data_id");
        try{
            //半消息发送成功 执行本地数据库操作
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return null;
    }
}*/

