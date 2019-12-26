/**
  ******************************************************************************
  * @file    main.c
  * @author  fire
  * @version V1.0
  * @date    2015-01-xx
  * @brief   WF-ESP8266 WiFiģ�����
  ******************************************************************************
  * @attention
  *
  * ʵ��ƽ̨:Ұ�� iSO STM32 ������ 
  * ��̳    :http://www.firebbs.cn
  * �Ա�    :https://fire-stm32.taobao.com
  *
  ******************************************************************************
  */ 
 
#include "stm32f10x.h"
#include "bsp_usart1.h"
#include "bsp_SysTick.h"
#include "bsp_esp8266.h"
#include "test.h"
#include "bsp_dht11.h"
 
char  string[ 300 ] = { 0 };
int wendu = 0;
/**
  * @brief  ������
  * @param  ��
  * @retval ��
  */
int main ( void )
{
	/* ��ʼ�� */
  USARTx_Config ();                                                              //��ʼ������1
	SysTick_Init ();                                                               //���� SysTick Ϊ 1ms �ж�һ�� 
	ESP8266_Init ();                                                               //��ʼ��WiFiģ��ʹ�õĽӿں�����
	DHT11_Init ();

	
	printf ( "\r\nҰ�� WF-ESP8266 WiFiģ���������\r\n" );                          //��ӡ����������ʾ��Ϣ
  printf ( "\r\n�������� ESP8266 ......\r\n" );

//	macESP8266_CH_ENABLE();
//	
//	ESP8266_AT_Test ();
//	
//	ESP8266_Net_Mode_Choose ( STA );

//  while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );	
//	
//	ESP8266_Enable_MultipleId ( DISABLE );
//	
//	while ( !	ESP8266_Link_Server ( enumTCP, macUser_ESP8266_TcpServer_IP, macUser_ESP8266_TcpServer_Port, Single_ID_0 ) );
//	
//	while ( ! ESP8266_UnvarnishSend () );
//	
//	printf ( "\r\n���� ESP8266 ���\r\n" );
	
  ESP8266_StaTcpClient_UnvarnishTest ();

  while ( 1 )
	{
		
			
    sprintf(string,"POST /devices/527330138/datapoints?type=3 HTTP/1.1\r\napi-key:f8YTS447=6AAcSVJJS=GhngUr2c=\r\nHost:api.heclouds.com\r\nContent-Length:11\r\n\r\n{%s%d}","\"Temp\":",77);
		ESP8266_SendString(ENABLE,string,0,5);
		Delay_ms(10000);
	};
	
	
}


/*********************************************END OF FILE**********************/
