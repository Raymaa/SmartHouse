/**
  ******************************************************************************
  * @file    main.c
  * @author  fire
  * @version V1.0
  * @date    2015-01-xx
  * @brief   WF-ESP8266 WiFi模块测试
  ******************************************************************************
  * @attention
  *
  * 实验平台:野火 iSO STM32 开发板 
  * 论坛    :http://www.firebbs.cn
  * 淘宝    :https://fire-stm32.taobao.com
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
  * @brief  主函数
  * @param  无
  * @retval 无
  */
int main ( void )
{
	/* 初始化 */
  USARTx_Config ();                                                              //初始化串口1
	SysTick_Init ();                                                               //配置 SysTick 为 1ms 中断一次 
	ESP8266_Init ();                                                               //初始化WiFi模块使用的接口和外设
	DHT11_Init ();

	
	printf ( "\r\n野火 WF-ESP8266 WiFi模块测试例程\r\n" );                          //打印测试例程提示信息
  printf ( "\r\n正在配置 ESP8266 ......\r\n" );

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
//	printf ( "\r\n配置 ESP8266 完毕\r\n" );
	
  ESP8266_StaTcpClient_UnvarnishTest ();

  while ( 1 )
	{
		
			
    sprintf(string,"POST /devices/527330138/datapoints?type=3 HTTP/1.1\r\napi-key:f8YTS447=6AAcSVJJS=GhngUr2c=\r\nHost:api.heclouds.com\r\nContent-Length:11\r\n\r\n{%s%d}","\"Temp\":",77);
		ESP8266_SendString(ENABLE,string,0,5);
		Delay_ms(10000);
	};
	
	
}


/*********************************************END OF FILE**********************/
