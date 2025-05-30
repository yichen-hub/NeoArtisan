package io.github.moyusowo.neoartisan.recipe;

import io.github.moyusowo.neoartisan.NeoArtisan;
import io.github.moyusowo.neoartisan.util.init.InitMethod;
import io.github.moyusowo.neoartisan.util.init.InitPriority;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

final class FurnaceBehavior implements Listener {

    private FurnaceBehavior() {}

    @InitMethod(priority = InitPriority.LISTENER)
    static void registerListener() {
        NeoArtisan.registerListener(new FurnaceBehavior());
    }

    @EventHandler
    private void onFurnace(FurnaceBurnEvent event) {
        if (event.isCancelled()) return;
        Furnace furnace = (Furnace) event.getBlock().getState();
        ItemStack fuel = furnace.getSnapshotInventory().getFuel();
        ItemStack smelting = furnace.getSnapshotInventory().getSmelting();
        if ((NeoArtisanAPI.getItemRegistry().isArtisanItem(smelting)) || (NeoArtisanAPI.getItemRegistry().isArtisanItem(fuel))) {
            event.setCancelled(true);
        }
    }
}
