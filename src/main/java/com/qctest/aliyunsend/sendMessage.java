package com.qctest.aliyunsend;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;

public class sendMessage {
   @SuppressWarnings("finally")
public static String  sendM(String accessId,String accessKey,String endPoint,String QueueName,String mge){
//	        CloudAccount account = new CloudAccount("aOX17Qr9vXVD9GJg", "OkKQtesmTz3jtfIM5lsVcfOcvI9s2Q", "http://1667348596157055.mqs-cn-hangzhou.aliyuncs.com");
	   		if((accessId==null||accessId.equals(""))||(accessKey==null||accessKey.equals(""))||(endPoint==null||endPoint.equals(""))){
	   			accessId = "sulgyCs1lNU420ra";
	   			accessKey = "QbXX86FSRUNHEMyEGXfHznJ00xeeop";
	   			endPoint = "http://1667348596157055.mqs-cn-hangzhou.aliyuncs.com";
	   		}
	   		CloudAccount account = new CloudAccount(accessId, accessKey, endPoint);
	   		
	   		MNSClient client = account.getMNSClient(); // 在程序中，CloudAccount以及MNSClient单例实现即可，多线程安全
	        try {
	            CloudQueue queue = client.getQueueRef(QueueName);
	            Message message = new Message();
//	            message.setMessageBody("[{\"appId\":\"84A7FDF4-0573-4775-A14B-00005BF55645\",\"fundId\":\"64A7FDF4-0573-4775-A14B-00005BF55645\",\"custId\":\"123456789\",\"custName\":\"段韵秋\",\"amount\":\"5000.01\",\"phoneNumber\":\"13917254058\",\"identificationType\":\"0\",\"identificationNumber\":\"510107199108082628\",\"bankAccount\":\"6222021001048938630\",\"bankName\":\"工商银行\",\"bankCode\":\"ICBC\",\"accountName\":\"段韵秋\"}]");
	            message.setMessageBody(mge);
	            Message putMsg = queue.putMessage(message);
	            System.out.println("Send message id is: " + putMsg.getMessageId());
	            String MgeId = putMsg.getMessageId();
	            return MgeId;
	        } catch (ClientException ce)
	        {
	            System.out.println("Something wrong with the network connection between client and MNS service."
	                   + "Please check your network and DNS availablity.");
	            ce.printStackTrace();
	        } catch (ServiceException ex)
	        {
	            if (ex.getErrorCode().equals("QueueNotExist"))
	            {
	                System.out.println("Queue is not exist.Please create before use");
	            } else if (ex.getErrorCode().equals("TimeExpired"))
	            {
	                System.out.println("The request is time expired. Please check your local machine timeclock");
	            }
	            ex.printStackTrace();
	        } catch (Exception e)
	        {
	            System.out.println("Unknown exception happened!");
	            e.printStackTrace();
	        }finally{
		        client.close();  // 程序退出时，需主动调用client的close方法进行资源释放
				return mge;
	        }
   }
}
