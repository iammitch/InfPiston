package mitch.infpist;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class InfinitePiston extends JavaPlugin {
    private final IPBlockListener blockListener = new IPBlockListener(this);

    // NOTE: There should be no need to define a constructor any more for more info on moving from
    // the old constructor see:
    // http://forums.bukkit.org/threads/too-long-constructor.5032/

    public void onDisable() {

    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.BLOCK_PISTON_EXTEND, blockListener, Priority.Normal, this);
    }

}