package mitch.infpist;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.Material;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

public class IPBlockListener extends BlockListener {
    private final InfinitePiston plugin;

    public IPBlockListener(final InfinitePiston plugin) {
        this.plugin = plugin;
    }
    
    private Block getAttachedBlock(Block b) {
        MaterialData m = b.getState().getData();
        BlockFace face = BlockFace.DOWN;
        if (m instanceof Attachable) {
            face = ((Attachable) m).getAttachedFace();
        }
        return b.getRelative(face);
    }

    private void checkFace(BlockPistonExtendEvent event, BlockFace face) {
		Sign sign = (Sign)event.getBlock().getRelative(face).getState();
		if (sign.getLine(0).equals("[InfPist]")) {
			String blockID = sign.getLine(1);
			if (Material.matchMaterial(blockID) != null) {
				Block b = event.getBlock().getRelative(event.getDirection());
				if (b.getType() == Material.AIR) {
					b.setType(Material.matchMaterial(blockID));
				}
			}
		}
    }
    
    @Override
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
    	if (event.isSticky()) {
    		return;
    	}
    	BlockFace forward = event.getDirection();
    	if (forward != BlockFace.NORTH && event.getBlock().getRelative(BlockFace.NORTH).getType() == Material.WALL_SIGN) {
    		checkFace(event, BlockFace.NORTH);
    	}
    	if (forward != BlockFace.SOUTH && event.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.WALL_SIGN) {
    		checkFace(event, BlockFace.SOUTH);
    	}
    	if (forward != BlockFace.EAST && event.getBlock().getRelative(BlockFace.EAST).getType() == Material.WALL_SIGN) {
    		checkFace(event, BlockFace.EAST);
    	}
    	if (forward != BlockFace.WEST && event.getBlock().getRelative(BlockFace.WEST).getType() == Material.WALL_SIGN) {
    		checkFace(event, BlockFace.WEST);
    	}
    }
}