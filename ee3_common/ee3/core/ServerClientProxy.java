package ee3.core;

import ee3.core.interfaces.IProxy;
import ee3.lib.Reference;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.MinecraftForge;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public enum ServerClientProxy {
	CLIENT("ee3.client.EEProxy"),
	SERVER("ee3.server.EEProxy");
	
	private String className;
	
	private ServerClientProxy(String proxyClassName) {
		className = proxyClassName;
	}
	
	private IProxy buildProxy() {
		try {
			return (IProxy)Class.forName(className).newInstance();
		} catch (Exception e) {
			ModLoader.getLogger().severe(Reference.LOGGER_PREFIX + "A fatal error has occured initializing Equivalent Exchange");
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}
	
	public static IProxy getProxy() {
		if (MinecraftForge.isClient()) {
			return CLIENT.buildProxy();
		}
		else {
			return SERVER.buildProxy();
		}
	}
}
