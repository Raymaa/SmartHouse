#include "test.h"
#include "bsp_esp8266.h"
#include "bsp_SysTick.h"
#include <stdio.h>  
#include <string.h>  
#include <stdbool.h>
#include "bsp_dht11.h"



volatile uint8_t ucTcpClosedFlag = 0;
extern int wendu;
extern char  string[ 300 ];

/**
  * @brief  ESP8266 （Sta Tcp Client）透传
  * @param  无
  * @retval 无
  */
	uint8_t ucStatus;
	
	char cStr [ 100 ] = { 0 };

	DHT11_Data_TypeDef DHT11_Data;
	void ESP8266_StaTcpClient_UnvarnishTest ( void )
{
  printf ( "\r\n正在配置 ESP8266 ......\r\n" );

	macESP8266_CH_ENABLE();
	
	ESP8266_AT_Test ();
	
	ESP8266_Net_Mode_Choose ( STA );

  ESP8266_Enable_MultipleId ( DISABLE );
	
	while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );	
	
//	ESP8266_Enable_MultipleId ( DISABLE );
	
	
	while ( !	ESP8266_Link_Server ( enumTCP, macUser_ESP8266_TcpServer_IP, macUser_ESP8266_TcpServer_Port, Single_ID_0 ) );

	while ( ! ESP8266_UnvarnishSend () );
	
	printf ( "\r\n配置 ESP8266 完毕\r\n" );
	
	USART_ITConfig ( USART3, USART_IT_RXNE, DISABLE );
	while ( 1 )
	{		
		if ( DHT11_Read_TempAndHumidity ( & DHT11_Data ) == SUCCESS )       //读取 DHT11 温湿度信息

		{sprintf ( cStr, "\r\n读取DHT11成功!\r\n\r\n湿度为%d.%d ％RH ，温度为 %d.%d℃ \r\n", 
								DHT11_Data .humi_int, DHT11_Data .humi_deci, DHT11_Data .temp_int, DHT11_Data.temp_deci );
			wendu = DHT11_Data .temp_int;
    sprintf(string,"POST /devices/527330138/datapoints?type=3 HTTP/1.1\r\napi-key:f8YTS447=6AAcSVJJS=GhngUr2c=\r\nHost:api.heclouds.com\r\nContent-Length:11\r\n\r\n{%s%d}","\"Temp\":",77);
		ESP8266_SendString(ENABLE,string,0,Single_ID_0);
		Delay_ms(8000);

		}		
		else
			sprintf ( cStr, "Read DHT11 ERROR!\r\n" );

		//printf ( "%s", cStr );                                             //打印读取 DHT11 温湿度信息

	
		//ESP8266_SendString ( ENABLE, cStr, 0, Single_ID_0 );               //发送 DHT11 温湿度信息到网络调试助手
		//ESP8266_SendString(ENABLE,"AAA",0,5);
		//Delay_ms ( 1000 );
		
//		if ( ucTcpClosedFlag )  
if ( ucTcpClosedFlag )		//检测是否失去连接
		{
			ESP8266_ExitUnvarnishSend ();                                    //退出透传模式
			
			do ucStatus = ESP8266_Get_LinkStatus ();                         //获取连接状态
			while ( ! ucStatus );
			
			if ( ucStatus == 4 )                                             //确认失去连接后重连
			{
				printf ( "\r\n正在重连热点和服务器 ......\r\n" );
				
				while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );
				
				while ( !	ESP8266_Link_Server ( enumTCP, macUser_ESP8266_TcpServer_IP, macUser_ESP8266_TcpServer_Port, Single_ID_0 ) );
				
				printf ( "\r\n重连热点和服务器成功\r\n" );

			}
			
			while ( ! ESP8266_UnvarnishSend () );		
			
		}

	}
	
		
}




