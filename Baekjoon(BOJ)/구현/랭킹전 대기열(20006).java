import java.io.*;
import java.util.*;

public class Main {

    int p;
    int m;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        HashMap<Integer, Room> rooms = new HashMap<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            boolean check = false;
            for(int j = 0 ; j < rooms.size();j++){
                Room room = rooms.get(j);
                if(Math.abs(room.level - level) <= 10 && room.players.size() < m){
                    room.players.add(new Player(level, name));
                    check = true;
                    break;
                }
            }

            if(!check){
                Room newRoom = new Room(level, new ArrayList<>());
                newRoom.players.add(new Player(level, name));
                rooms.put(rooms.size(), newRoom);
            }
        }

        for(int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            if(room.players.size() == m){
                System.out.println("Started!");
            }
            else{
                System.out.println("Waiting!");
            }

            Collections.sort(room.players, (p1, p2) -> p1.name.compareTo(p2.name));
            for(int j = 0 ; j  < room.players.size();j++){
                Player player = room.players.get(j);
                System.out.println(player.level + " " + player.name);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    class Room {
        int level;
        List<Player> players;

        public Room(int level, List<Player> players) {
            this.level = level;
            this.players = players;
        }
    }

    class Player{
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }
}
