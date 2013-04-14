/*
  Web client
 
 This sketch connects to a website (http://www.google.com)
 using an Arduino Wiznet Ethernet shield. 
 
 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13
 
 created 18 Dec 2009
 modified 9 Apr 2012
 by David A. Mellis
 
 */
#include <SHT1x.h>
#include <SPI.h>
#include <Ethernet.h>
#define dataPin  2   // DATA
#define clockPin 3   // SCK
SHT1x sht1x(dataPin, clockPin);

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = {  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress server(192,168,1,116); // Google

const int analogInPin = A0;  // Analog input pin that the potentiometer is attached to

void setup() {
 // Open serial communications and wait for port to open:
  Serial.begin(9600);
   while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }

  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    for(;;)
      ;
  }

}

void loop()
{
    delay(1000);
    Serial.println("loop");
    float  temp_c = sht1x.readTemperatureC();
    float  humidity = sht1x.readHumidity();
    int sensorValue = analogRead(analogInPin);            

    EthernetClient client;
    Serial.println("connecting...");
    if (client.connect(server, 9000)) {
        Serial.println("connected");
        // Make a HTTP request:
        client.print("PUT /status?plantId=516a1b913004cfda1a617a0c&h=");
//        client.print("GET /app/test");
        client.print(humidity);
        client.print("&t=");
        client.print(temp_c);
        client.print("&r=");
        client.print(sensorValue);
        client.println(" HTTP/1.0");
        client.println();
        delay(1000);
        client.stop();
    }else {
      Serial.println("connection failed");
    }
}


// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
IPAddress ip(192,168,1, 233);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
//EthernetServer server(80);



