package taojava.assignment6.problem1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LoopingArrayBasedQueue<T>
    extends
      ArrayBasedQueue<T>
{

  // /FIELDS

  int back;

  public LoopingArrayBasedQueue(int capacity) throws Exception
  {
    super(capacity);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public boolean isFull()
  {
    return (size >= values.length);
  }

  @Override
  public void put(T val) throws Exception
  {
    if (size == this.values.length)
      {
        throw new Exception("Array is full, cannot add element");
      }
    
    this.values[this.back] = val;
    this.size++;
    this.back = (this.back+1) % this.values.length;
  }

  @Override
  public T get() throws Exception
  {
    if (size == 0)
      {
        throw new Exception("No elements in array, cannot return element");
      }
    
    T output = this.values[this.front];
    this.values[this.front] = null;
    this.front = (this.front + 1) % this.values.length;
    this.size--;
    return output;
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}


class LoopingArrayBasedQueueIterator<T>
implements Iterator<T>
{
// +--------+----------------------------------------------------------
// | Fields |
// +--------+

  int location;
  LoopingArrayBasedQueue<T> myQueue;
  
// +--------------+----------------------------------------------------
// | Constructors |
// +--------------+
  

  /**
   * Create a new iterator.
   */
  public LoopingArrayBasedQueueIterator(LoopingArrayBasedQueue<T> q)
  {
    this.location = 0;
    this.myQueue = q;
    //Works, maybe?
    
  } // ArrayBasedQueueIterator

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public T next()
    throws NoSuchElementException
  {
    if (!this.hasNext())
      {
        throw new NoSuchElementException("no elements remain");
      } // if no elements 
    else
      {
        int temp = location;
        location = ((location + 1) % myQueue.values.length);
        return myQueue.values[temp];
      }
  } // next()

  @Override
  public boolean hasNext()
  {
   if (myQueue.values[location] == null)
    return false;
   else
     {
     return true;
     }
  } // hasNext()

  @Override
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  } // remove()
} // ArrayBasedQueueIterator<T>
