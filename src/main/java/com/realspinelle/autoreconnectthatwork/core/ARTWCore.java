package com.realspinelle.autoreconnectthatwork.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.realspinelle.autoreconnectthatwork.mixins.Mixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({ "com.realspinelle.autoreconnectthatwork.core" })
public class ARTWCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    private static Boolean isObf = null;
    private String[] transformerClasses;

    @Override
    public String getMixinConfig() {
        return "mixins.autoreconnectthatwork.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return IMixins.getEarlyMixins(Mixins.class, loadedCoreMods);
    }

    @Override
    public String[] getASMTransformerClass() {
        return transformerClasses;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static boolean isObf() {
        if (isObf == null) {
            throw new IllegalStateException("Obfuscation state has been accessed too early!");
        }
        return isObf;
    }

    public static void logASM(Logger log, String message) {
        if (isObf == null || isObf) {
            log.debug(message);
        } else {
            log.info(message);
        }
    }
}
