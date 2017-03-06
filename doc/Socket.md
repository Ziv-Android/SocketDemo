Socket

1. TCP/IP协议
TCP/IP协议（传输控制协议）由网络层的IP协议和传输层的TCP协议组成。IP层负责网络主机的定位，数据传输的路由，由IP地址可以唯一的确定Internet上的一台主机。TCP层负责面向应用的可靠的或非可靠的数据传输机制，这是网络编程的主要对象。

2. TCP与UDP
2.1 TCP与UDP区别：
    TCP特点：
        TCP是面向连接的协议，通过三次握手建立连接，通讯完成时要拆除连接，由于TCP是面向连接协议，所以只能用于点对点的通讯。而且建立连接也需要消耗时间和开销。
        TCP传输数据无大小限制，进行大数据传输。
        TCP是一个可靠的协议，它能保证接收方能够完整正确地接收到发送方发送的全部数据。
    UDP特点：
        UDP是面向无连接的通讯协议，UDP数据包括目的端口号和源端口号信息，由于通讯不需要连接，所以可以实现广播发送。
        UDP传输数据时有大小限制，每个被传输的数据报必须限定在64KB之内。
        UDP是一个不可靠的协议，发送方所发送的数据报并不一定以相同的次序到达接收方。
2.2 TCP与UDP应用：
    TCP在网络通信上有极强的生命力，例如远程连接（Telnet）和文件传输（FTP）都需要不定长度的数据被可靠地传输。但是可靠的传输是要付出代价的，对数据内容正确性的检验必然占用计算机的处理时间和网络的带宽，因此TCP传输的效率不如UDP高。
    UDP操作简单，而且仅需要较少的监护，因此通常用于局域网高可靠性的分散系统中client/server应用程序。例如视频会议系统，并不要求音频视频数据绝对的正确，只要保证连贯性就可以了，这种情况下显然使用UDP会更合理一些。

3. Socket是什么
Socket通常也称作"套接字"，用于描述IP地址和端口，是一个通信链的句柄。网络上的两个程序通过一个双向的通讯连接实现数据的交换，这个双向链路的一端称为一个Socket，一个Socket由一个IP地址和一个端口号唯一确定。应用程序通常通过"套接字"向网络发出请求或者应答网络请求。 Socket是TCP/IP协议的一个十分流行的编程界面，但是，Socket所支持的协议种类也不光TCP/IP一种，因此两者之间是没有必然联系的。在Java环境下，Socket编程主要是指基于TCP/IP协议的网络编程。
Socket通讯过程：服务端监听某个端口是否有连接请求，客户端向服务端发送连接请求，服务端收到连接请求向客户端发出接收消息，这样一个连接就建立起来了。客户端和服务端都可以相互发送消息与对方进行通讯。

Socket的基本工作过程包含以下四个步骤：
    创建Socket；
    打开连接到Socket的输入输出流；
    按照一定的协议对Socket进行读写操作；
    关闭Socket。

4. Java中的Socket
java.net下有两个类：Socket和ServerSocket
构造方法                                                                      | 详情
---------------------------------------------------------------------------- | --------------------------------------
Socket(InetAddress address, int port)                                        |
Socket(String host, int port)                                                |
Socket(InetAddress address, int port, InetAddress localAddr, int localPort)  |
Socket(String host, int port, InetAddress localAddr, int localPort)          |
Socket(SocketImpl impl)                                                      |
                                                                             |
SocketServer(int port)                                                       |
SocketServer(int port, int backlog)                                          |
SocketServer(int port, int backlog, InetAddress bindAddr)                    |

0~1023的端口号为系统所保留，例如http服务的端口号为80,telnet服务的端口号为21,ftp服务的端口号为23