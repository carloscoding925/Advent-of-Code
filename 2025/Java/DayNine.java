import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class DayNine {
    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();

        return;
    }

    private static void solvePartOne() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayNine.txt"));

            List<long[]> coordinates = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(",");
                long col = Long.parseLong(parts[0]);
                long row = Long.parseLong(parts[1]);
                coordinates.add(new long[]{col, row});
            }

            long maxRectangle = 0;
            for (int i = 0; i < coordinates.size(); i++) {
                for (int j = i + 1; j < coordinates.size(); j++) {
                    long[] corner = coordinates.get(i);
                    long[] otherCorner = coordinates.get(j);

                    long width = Math.abs(corner[0] - otherCorner[0]) + 1;
                    long height = Math.abs(corner[1] - otherCorner[1]) + 1;
                    long area = width * height;

                    maxRectangle = Math.max(maxRectangle, area);
                }
            }

            System.out.println("Part 1 Output: " + maxRectangle);
            // Should be: 4764078684
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static class Point {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    private static void solvePartTwo() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("../Inputs/DayNine.txt"));

            List<Point> points = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(",");
                long x = Long.parseLong(parts[0]);
                long y = Long.parseLong(parts[1]);
                points.add(new Point(x, y));
            }

            int n = points.size();

            TreeMap<Long, TreeSet<Long>> northWalls = new TreeMap<>();
            TreeMap<Long, TreeSet<Long>> eastWalls = new TreeMap<>();
            TreeMap<Long, TreeSet<Long>> southWalls = new TreeMap<>();
            TreeMap<Long, TreeSet<Long>> westWalls = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                Point a = points.get((i - 1 + n) % n);
                Point b = points.get(i);
                Point c = points.get((i + 1) % n);

                Direction abDirection = getDirection(a, b);
                Direction bcDirection = getDirection(b, c);

                boolean isConvex = isConvexCorner(abDirection, bcDirection);

                switch (bcDirection) {
                    case NORTH:
                        for (Point p : points) {
                            if (p.y < b.y && p.y > c.y) {
                                westWalls.computeIfAbsent(p.y, k -> new TreeSet<>()).add(-b.x);
                            }
                        }
                        if (isConvex) {
                            southWalls.computeIfAbsent(b.x, k -> new TreeSet<>()).add(b.y);
                            westWalls.computeIfAbsent(b.y, k -> new TreeSet<>()).add(-b.x);
                        }
                        break;
                    case EAST:
                        for (Point p : points) {
                            if (p.x > b.x && p.x < c.x) {
                                northWalls.computeIfAbsent(p.x, k -> new TreeSet<>()).add(-b.y);
                            }
                        }
                        if (isConvex) {
                            westWalls.computeIfAbsent(b.y, k -> new TreeSet<>()).add(-b.x);
                            northWalls.computeIfAbsent(b.x, k -> new TreeSet<>()).add(-b.y);
                        }
                        break;
                    case SOUTH:
                        for (Point p : points) {
                            if (p.y > b.y && p.y < c.y) {
                                eastWalls.computeIfAbsent(p.y, k -> new TreeSet<>()).add(b.x);
                            }
                        }
                        if (isConvex) {
                            northWalls.computeIfAbsent(b.x, k -> new TreeSet<>()).add(-b.y);
                            eastWalls.computeIfAbsent(b.y, k -> new TreeSet<>()).add(b.x);
                        }
                        break;
                    case WEST:
                        for (Point p : points) {
                            if (p.x < b.x && p.x > c.x) {
                                southWalls.computeIfAbsent(p.x, k -> new TreeSet<>()).add(b.y);
                            }
                        }
                        if (isConvex) {
                            eastWalls.computeIfAbsent(b.y, k -> new TreeSet<>()).add(b.x);
                            southWalls.computeIfAbsent(b.x, k -> new TreeSet<>()).add(b.y);
                        }
                        break;
                }
            }

            List<Long> pointDistanceToNorthWall = new ArrayList<>();
            List<Long> pointDistanceToEastWall = new ArrayList<>();
            List<Long> pointDistanceToSouthWall = new ArrayList<>();
            List<Long> pointDistanceToWestWall = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                Point p = points.get(i);

                TreeSet<Long> nWalls = northWalls.get(p.x);
                if (nWalls != null) {
                    Long wall = nWalls.ceiling(-p.y);
                    pointDistanceToNorthWall.add(wall == null ? 0L : (wall + p.y) + 1);
                } 
                else {
                    pointDistanceToNorthWall.add(0L);
                }

                TreeSet<Long> eWalls = eastWalls.get(p.y);
                if (eWalls != null) {
                    Long wall = eWalls.ceiling(p.x);
                    pointDistanceToEastWall.add(wall == null ? 0L : (wall - p.x) + 1);
                } 
                else {
                    pointDistanceToEastWall.add(0L);
                }

                TreeSet<Long> sWalls = southWalls.get(p.x);
                if (sWalls != null) {
                    Long wall = sWalls.ceiling(p.y);
                    pointDistanceToSouthWall.add(wall == null ? 0L : (wall - p.y) + 1);
                } 
                else {
                    pointDistanceToSouthWall.add(0L);
                }

                TreeSet<Long> wWalls = westWalls.get(p.y);
                if (wWalls != null) {
                    Long wall = wWalls.ceiling(-p.x);
                    pointDistanceToWestWall.add(wall == null ? 0L : (wall + p.x) + 1);
                } 
                else {
                    pointDistanceToWestWall.add(0L);
                }
            }

            long answer = 0;
            for (int aIndex = 0; aIndex < n; aIndex++) {
                for (int bIndex = aIndex + 1; bIndex < n; bIndex++) {
                    Point a = points.get(aIndex);
                    Point b = points.get(bIndex);

                    long potentialWidth = Math.abs(b.x - a.x) + 1;
                    long potentialHeight = Math.abs(b.y - a.y) + 1;

                    if (b.y < a.y) {
                        if (Math.min(pointDistanceToNorthWall.get(aIndex),
                                    pointDistanceToSouthWall.get(bIndex)) < potentialHeight) {
                            continue;
                        }
                    } else if (b.y > a.y) {
                        if (Math.min(pointDistanceToSouthWall.get(aIndex),
                                    pointDistanceToNorthWall.get(bIndex)) < potentialHeight) {
                            continue;
                        }
                    }

                    if (b.x > a.x) {
                        if (Math.min(pointDistanceToEastWall.get(aIndex),
                                    pointDistanceToWestWall.get(bIndex)) < potentialWidth) {
                            continue;
                        }
                    } else if (b.x < a.x) {
                        if (Math.min(pointDistanceToWestWall.get(aIndex),
                                    pointDistanceToEastWall.get(bIndex)) < potentialWidth) {
                            continue;
                        }
                    }

                    answer = Math.max(answer, potentialWidth * potentialHeight);
                }
            }

            System.out.println("Part 2 Output: " + answer);
            // Should be:
        } catch (Exception ex) {
            System.out.println("Caught Exception: " + ex);
        }
    }

    private static Direction getDirection(Point a, Point b) {
        if (b.y < a.y) {
            return Direction.NORTH;
        }

        if (b.x > a.x) {
            return Direction.EAST;
        }

        if (b.y > a.y) {
            return Direction.SOUTH;
        }

        if (b.x < a.x) {
            return Direction.WEST;
        }

        return null;
    }

    private static boolean isConvexCorner(Direction a, Direction b) {
        return (a == Direction.NORTH && b == Direction.EAST) ||
                (a == Direction.EAST && b == Direction.SOUTH) ||
                (a == Direction.SOUTH && b == Direction.WEST) ||
                (a == Direction.WEST && b == Direction.NORTH);
    }
}
