package com.example.gerbo.wavealgoritm4;

/**
 * Created by LabAT on 09.02.2018.
 */

public class PathFinder {

    private static int[][] pathMap;
    private static int pathMapSizeY, pathMapSizeX;
    private static int startY, startX, stopY, stopX;
    private static boolean iFind;
    private static int waveCount;

    public static int[][] findYourWay(int[][] map, int ySize, int xSize) {

        pathMapSizeY = ySize;
        pathMapSizeX = xSize;
        if (pathMap == null) pathMap = new int[pathMapSizeY][pathMapSizeX];
        pathMapInitialization(map);

        makeTheWaves();
        buildThePath(stopY, stopX);

        return pathMap;
    }

    private static void pathMapInitialization (int[][] map) {
        for (int y = 0; y < pathMapSizeY; y++) {
            for (int x = 0; x < pathMapSizeX; x++) {
                switch (map[y][x]) {
                    case 0:
                        pathMap[y][x] = 0;
                        break;
                    case -1:
                        pathMap[y][x] = -1;
                        break;
                    case -2:
                        pathMap[y][x] = -2;
                        startY = y;
                        startX = x;
                        break;
                    case -3:
                        pathMap[y][x] = -3;
                        stopY = y;
                        stopX = x;
                        break;
                    case -4:
                        pathMap[y][x] = 0;
                        break;
                }
            }
        }
    }

    private static boolean cellChecker (int y, int x, int numb) {
        return pathMap[y][x] == numb;
    }

    private static void cellSet (int y, int x, int numb) {
        pathMap[y][x] = numb;
    }

    private static boolean isThisFinish (int y, int x) {
        return (stopX == x) && (stopY == y);
    }

    private static boolean isThisStart (int y, int x) {
        return (startX == x) && (startY == y);
    }

    private static void makeTheWaves () {

        waveCount = 1;
        iFind = false;

        lookAround(startY, startX, 1);

        while (!iFind) {
            for (int y = 0; y < pathMapSizeY; y++) {
                for (int x = 0; x < pathMapSizeX; x++) {

                    if (pathMap[y][x] == waveCount) lookAround(y, x, waveCount + 1);
                    if (iFind) return;

                }
            }
            waveCount++;
        }
    }

    private static void lookAround (int y, int x, int newNumb) {
        if (y != 0){
            if (!isThisFinish(y - 1, x)) {if (cellChecker(y - 1, x, 0)) cellSet(y - 1, x, newNumb);}
        else {iFind = true; return;}
        }
        if (y != pathMapSizeY - 1){
            if (!isThisFinish(y + 1, x)) {if (cellChecker(y + 1, x, 0)) cellSet(y + 1, x, newNumb);}
        else {iFind = true; return;}
        }
        if (x != 0){
            if (!isThisFinish(y, x - 1)) {if (cellChecker(y, x - 1, 0)) cellSet(y, x - 1, newNumb);}
        else {iFind = true; return;}
        }
        if (x != pathMapSizeX - 1){
            if (!isThisFinish(y, x + 1)) {if (cellChecker(y, x + 1, 0)) cellSet(y, x + 1, newNumb);}
        else {iFind = true;}
        }
    }

    private static void buildThePath (int y, int x) {
        if (y !=0 && (isThisStart(y - 1, x))) {
            pathMap[y][x] = -4;
            return;
        } else
            if (y != 0 && (cellChecker(y - 1, x, waveCount))) {
                pathMap[y][x] = -4; waveCount--;
                buildThePath(y - 1, x);
                return;
            }
        if (y != pathMapSizeY - 1 && (isThisStart(y + 1, x))) {
            pathMap[y][x] = -4;
            return;
        } else
            if (y != pathMapSizeY - 1 && (cellChecker(y + 1, x, waveCount))) {
                pathMap[y][x] = -4; waveCount--;
                buildThePath(y + 1, x);
                return;
            }
        if (x != 0 && (isThisStart(y, x - 1))) {
            pathMap[y][x] = -4;
            return;
        } else
            if (x != 0 && (cellChecker(y, x - 1, waveCount))) {
                pathMap[y][x] = -4; waveCount--;
                buildThePath(y, x - 1);
                return;
            }
        if (x != pathMapSizeX - 1 && (isThisStart(y, x + 1))) {
            pathMap[y][x] = -4;
        } else
            if (x != pathMapSizeX - 1 && (cellChecker(y, x + 1, waveCount))) {
                pathMap[y][x] = -4; waveCount--;
                buildThePath(y, x + 1);
            }
    }
}
