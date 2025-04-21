# Balance Binary Tree

Balances an unbalanced binary tree

public class StBalancer {

    public void main(String[] args) {

        StBalancer balancer = new StBalancer();
        List<Node> nodes = balancer.captureInput().stream().map(input -> new Node(input)).toList();

        Node root = nodes.get(0);

        for(int i = 1; i < nodes.size(); i++) {
            root.add(nodes.get(i));
        }

        List<Node> visitedNodes = new ArrayList<Node>();
        root.vizit(visitedNodes::add);

        int beginning = 0;

        List<Integer> sortedNodes = visitedNodes
            .stream()
            .sorted(Comparator::comparing());

        Node newRoot = buildBalancedTree(0, sortedNodes);
    }

    private Node buildBalancedTree(int beginning, List<Node> sortedNodes) {

        int middle = findMiddle(beginning, sortedNodes.size());

        if (middle <= beginning) {
            return null;
        }

        middleNode = sortedNodes.get(middle);
        buildBalancedTree(beginning, sortedNodes.subList(begining, middle + 1));
        buildBalancedTree(middle + 1, sortedNodes.subList(middle + 1, sortedNodes.size()));
        return middleNode;
    }

    private int findMiddle(Integer beginning, Integer end) {
        return (end - beginning) / 2;
    }

    private List<Integer> captureInput() {
    
        List<Integer> integers = new ArrayList<>();

        Scanner scan = new Scanner();
        
        do {
        
            String input = scan.next();

            if (!isDigit(input)) {
                break;
            }

            integers.add(Integer.parse(input));

        } while (true)

        return integers;
    }

    private boolean isDigit(String input) {
        return Arrays.listOf(input.toCharArray()).stream.anyMatch(c -> Character.isDigit(c));
    }

    
    // 2. Balance the tree

}

class Node implements Comparable {

    private Integer digit;

    Node leftChild;
    Node rightChild;

    public Node(Integer digit) {
        this.digit = digit;
    }

    public int compareTo(Node l, Node r) {
        return l.getDigit().compareTo(r.getDigit());
    }

    public void visit(Consumer<Node> consumer) {
        
        consumer.accept(this);

        if (this.left != null) {
            visit(this.leftChild);
        }

        if (this.right != null) {
            visit(this.rightChild);
        }
    }

    private add(Node newNode) {

        int compare = newNode.compareTo(this);

        if (compare == 0) {
            return;
        }

        if (compare < 0) {

            if (this.leftChild != null) {
                add(newNode);
            } else {
                this.leftChild = newNode;
            }

            return;
        }

        if (this.rightChild != null) {
            add(newNode);
        } else {
            root.rightChild = newNode;
        }
    }
}
