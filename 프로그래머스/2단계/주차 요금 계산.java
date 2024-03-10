import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, Integer> parkingCars = new HashMap<>();
        TreeMap<Integer, Integer> totalTime = new TreeMap<>();
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < records.length;i++){
            String[] record = records[i].split(" ");
            int time = getTime(record[0]);
            int carNumber = Integer.parseInt(record[1]);
            String status = record[2];

            if(status.equals("IN")){
                parkingCars.put(carNumber, time);
            }
            else{ // 출차 차량일때
                int duringTime = time - parkingCars.get(carNumber);
                parkingCars.remove(carNumber);
                totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + duringTime);
            }
        }

        for(Integer carNumber : parkingCars.keySet()){
            int duringTime = 60*23 + 59 - parkingCars.get(carNumber);
            totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + duringTime);
        }

        for(Integer carNumber : totalTime.keySet()){
            int charge = fees[1];
            if(totalTime.get(carNumber) > fees[0]){
                charge += Math.ceil((double)(totalTime.get(carNumber) - fees[0]) / fees[2]) * fees[3];
            }
            result.add(charge);
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();

    }

    int getTime(String record){
        String[] times = record.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

}
