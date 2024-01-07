package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 61;
    public static final int HEIGHT = 61;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        drawMenu();
        while(true){
            String input=solicitNCharsInput(1);
            if(input.equals("n")){
                StdDraw.clear(Color.black);
                StdDraw.text(WIDTH/2,HEIGHT/2,"Please input seed:");
                StdDraw.show();
                break;
            } else if (input.equals("q")) {
                System.exit(0);
            }
        }
        String input="";
        while(true){
            if(!StdDraw.hasNextKeyTyped()){
                continue;
            }
            char thiskey=StdDraw.nextKeyTyped();
            if(input.length()>0&&thiskey=='s')
                break;
            if(thiskey>'0'&&thiskey<='9') {
                input += thiskey;
                StdDraw.clear(Color.black);
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Please input seed:");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, input);
                StdDraw.show();
            }
        }

        long seed=Long.parseLong(input);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.WALL;
            }
        }
        //生成随机房间
        Generate generate=new Generate(seed,WIDTH,HEIGHT);
        generate.GenerateRoom(finalWorldFrame,ter);
        generate.initHallways(finalWorldFrame,ter);
        generate.connectRegions(finalWorldFrame,ter);
        generate.modifyHallway(finalWorldFrame);
        Position player=generate.start(finalWorldFrame);
        ter.renderFrame(finalWorldFrame);
        UserInteract userInteract=new UserInteract(finalWorldFrame,ter,player);
        userInteract.interact();


    }
    private void drawMenu(){
        StdDraw.setCanvasSize(this.WIDTH * 16, this.HEIGHT * 16);;
        Font bigfont=new Font("Monaco",Font.BOLD,50);
        Font smallfont=new Font("Monaco",Font.BOLD,30);
        StdDraw.setXscale(0, this.WIDTH);
        StdDraw.setYscale(0, this.HEIGHT);
        StdDraw.clear(Color.black);
        StdDraw.enableDoubleBuffering();
        int midH=HEIGHT/2;
        int midW=WIDTH/2;
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(bigfont);
        StdDraw.text(midW,0.75*HEIGHT,"CS61B: THE GAME");
        StdDraw.setFont(smallfont);
        StdDraw.text(midW,midH+2,"New Game (N)");
        StdDraw.text(midW,midH,"Load Game (L)");
        StdDraw.text(midW,midH-2,"Quit (Q)");
        StdDraw.show();
    }
    private String solicitNCharsInput(int n) {
        String input="";
        while(input.length()<n){
            if(!StdDraw.hasNextKeyTyped()){
                continue;
            }
            input+=StdDraw.nextKeyTyped();
        }
        return input;
    }
    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        int tempindex = input.length();
        for(int i=1;i<input.length();i++){
            if(input.charAt(i)>'9'||input.charAt(i)<'0'){
                tempindex=i;
            }
        }
        long seed=Long.parseLong(input.substring(1,tempindex));
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.WALL;
            }
        }
        //生成随机房间
        Generate generate=new Generate(seed,WIDTH,HEIGHT);
        generate.GenerateRoom(finalWorldFrame,ter);
        generate.initHallways(finalWorldFrame,ter);
        generate.connectRegions(finalWorldFrame,ter);
        generate.modifyHallway(finalWorldFrame);

        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

}
