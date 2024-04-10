package Graphics;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class AssetsLoading {
    private final String path = "..\\Assets\\";
    private final String[] assets_list = {"White_Pawn","White_Rook","White_Knight","White_Bishop","White_Queen","White_King","Black_Pawn","Black_Rook","Black_Knight","Black_Bishop","Black_Queen","Black_King","Dark_Tiles","Light_Tiles"};
    private final HashMap<String,String> assets = new HashMap<String,String>();
    private HashMap<String,ImageIcon> assets_images = new HashMap<String,ImageIcon>();

    public AssetsLoading(){
        AssetsGetDirectory();
        loadImage();
    }

    private void loadImage() {
        Image image = null;
        for (String asset : assets_list) {
            try {
                image = new ImageIcon(getClass().getResource(path + assets.get(asset))).getImage();
            } catch (Exception e) {
                System.out.println("Error loading image: " + asset);
            }
            assets_images.put(asset, new ImageIcon(image));
        }
    }

    public ImageIcon getImage(String asset){
        return assets_images.get(asset);
    }

    private void AssetsGetDirectory(){
        assets.put("White_Pawn","w_pawn_png_shadow_128px.png");
        assets.put("White_Rook","w_rook_png_shadow_128px.png");
        assets.put("White_Knight","w_knight_png_shadow_128px.png");
        assets.put("White_Bishop","w_bishop_png_shadow_128px.png");
        assets.put("White_Queen","w_queen_png_shadow_128px.png");
        assets.put("White_King","w_king_png_shadow_128px.png");
        assets.put("Black_Pawn","b_pawn_png_shadow_128px.png");
        assets.put("Black_Rook","b_rook_png_shadow_128px.png");
        assets.put("Black_Knight","b_knight_png_shadow_128px.png");
        assets.put("Black_Bishop","b_bishop_png_shadow_128px.png");
        assets.put("Black_Queen","b_queen_png_shadow_128px.png");
        assets.put("Black_King","b_king_png_shadow_128px.png");
        assets.put("Dark_Tiles","brown_dark_png_shadow_128px.png");
        assets.put("Light_Tiles","brown_light_png_shadow_128px.png");
    }

}
