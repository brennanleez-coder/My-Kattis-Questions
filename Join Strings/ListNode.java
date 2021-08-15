public class ListNode {
    public String item;
    public ListNode next;
    public ListNode head;

    ListNode(String item) {
        this.item = item;
        this.next=null;
        this.head=this;
    }

    ListNode(String item, ListNode head, ListNode n) {
        this.item = item;
        this.next = n;
        this. head = head;
    }

    public ListNode getNext() { return this.next; }

	public String getItem() { return this.item; }

	public void setItem(String val) { this.item = val; }

}