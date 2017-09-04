package monitor.network;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import monitor.commonutils.OSType;

public class NetworkUtilsBeanFactory implements FactoryBean<NetworkFactory>, InitializingBean {

	
	private NetworkFactory factory = null;
	private NetworkFactory linuxFactory;
	private NetworkFactory windowsFactory;
	
	@Override
	public NetworkFactory getObject() throws Exception {
		// TODO Auto-generated method stub
		return factory;
	}

	@Override
	public Class<NetworkFactory> getObjectType() {
		// TODO Auto-generated method stub
		return NetworkFactory.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	   if(OSType.isLinux()) factory = linuxFactory;  
	   if(OSType.isWindows()) factory =  windowsFactory;

		
	}

	public NetworkFactory getLinuxFactory() {
		return linuxFactory;
	}

	public void setLinuxFactory(NetworkFactory linuxFactory) {
		this.linuxFactory = linuxFactory;
	}

	public NetworkFactory getWindowsFactory() {
		return windowsFactory;
	}

	public void setWindowsFactory(NetworkFactory windowsFactory) {
		this.windowsFactory = windowsFactory;
	}

}
