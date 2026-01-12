package io.github.zihengl.tetris.controllers.components;


import io.github.zihengl.tetris.models.enums.Tetros;
import javafx.scene.image.Image;

/**
 * Class: ImageAsset
 * Created on: 1/12/2026
 * Description: Created a new Enum class for assets due
 * to the fact that I decided to remove EMPTY from
 * the list of Tetros.
 *
 * @author liuzi | Zi heng Liu
 */

public enum ImageAsset {

    EMPTY("/io/github/zihengl/tetris/img/EMPTY.jpg", null),
    J("/io/github/zihengl/tetris/img/J.jpg", "/io/github/zihengl/tetris/img/ICON_J.png"),
    L("/io/github/zihengl/tetris/img/L.jpg", "/io/github/zihengl/tetris/img/ICON_L.png"),
    S("/io/github/zihengl/tetris/img/S.jpg", "/io/github/zihengl/tetris/img/ICON_S.png"),
    T("/io/github/zihengl/tetris/img/T.jpg", "/io/github/zihengl/tetris/img/ICON_T.png"),
    Z("/io/github/zihengl/tetris/img/Z.jpg", "/io/github/zihengl/tetris/img/ICON_Z.png"),
    I("/io/github/zihengl/tetris/img/I.jpg", "/io/github/zihengl/tetris/img/ICON_I.png"),
    O("/io/github/zihengl/tetris/img/O.jpg", "/io/github/zihengl/tetris/img/ICON_O.png");

    public final Image image;
    public final Image icon;

    private ImageAsset(String path, String iconPath) {
        this.image = new Image(getClass().getResourceAsStream(path));
        this.icon = iconPath == null ? null : new Image(getClass().getResourceAsStream(iconPath));
    }

    public static Image getImage(Tetros type) {
        if (type == null)
            return EMPTY.image;

        return ImageAsset.valueOf(type.name()).image;
    }

    public static Image getIcon(Tetros type) {
        return ImageAsset.valueOf(type.name()).icon;
    }
}
