package com.lym.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	private static final int PORT = 7777;

	public static void main(String[] args) {
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		try {
			// 5ÃëÐÄÌø
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);
			acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"))));
			acceptor.setHandler(new MinaHandler());
			acceptor.bind(new InetSocketAddress(PORT));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
