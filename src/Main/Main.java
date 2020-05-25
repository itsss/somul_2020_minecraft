package somul2020;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("플러그인이 활성화되었습니다.");
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlockPlaced().getType().equals(Material.FIRE)) {
            System.out.println("Fire Install");
            Player player = e.getPlayer();
            World world = player.getWorld();
            double x1 = e.getBlockPlaced().getLocation().getBlockX();
            double y1 = e.getBlockPlaced().getLocation().getBlockY();
            double z1 = e.getBlockPlaced().getLocation().getBlockZ();
            Block b1 = new Location(world, x1, y1-1, z1).getBlock(); //(x,y-1,z)좌표: 폭탄블럭을 의미함
            if(b1.getType() == Material.WOOL) //만약 블럭이 WOOL인 경우 (폭탄 블럭)
            {
                System.out.println("wool boom");
                Wool wool = new Wool(b1.getType(), b1.getData());
                DyeColor color = wool.getColor(); //양털의 색깔을 불러옴
                if(color.equals(DyeColor.ORANGE)) //양털의 색깔이 주황색인 경우 (주황색 양털 = 폭탄)
                {
                    System.out.println("BOOM!");
                    player.getWorld().createExplosion(b1.getLocation(), 500F); //폭발 발생 (크리퍼 4F)
                    player.getWorld().playEffect(b1.getLocation(), Effect.MOBSPAWNER_FLAMES, 10); //BOOM! 효과음 발생
                }
            }
        }
    }
}

//package somul2020;
//
//        import org.bukkit.Location;
//        import org.bukkit.World;
//        import org.bukkit.command.Command;
//        import org.bukkit.command.CommandExecutor;
//        import org.bukkit.command.CommandSender;
//        import org.bukkit.entity.Player;
//        import org.bukkit.plugin.java.JavaPlugin;
//
//public class Main extends JavaPlugin implements CommandExecutor {
//    @Override
//    public void onEnable() {
//        System.out.println("플러그인이 활성화되었습니다");
//        getCommand("tp").setExecutor(this);
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
//        if(cmd.getName().equalsIgnoreCase("tp")) {
//            Player player = (Player) sender;
//            double x = player.getLocation().getX();
//            double y = player.getLocation().getY();
//            double z = player.getLocation().getZ();
//            World world = player.getWorld();
//            player.teleport(new Location(world, x, y, z+5));
//        }
//        return true;
//    }
//}