import org.junit.Test;

import static org.junit.Assert.*;

public class FragmentTest {

  @Test
  public void setDenominator_DenomintorIsZero() {
    final int EXPECTED = 1;
    Fragment frag = new Fragment();
    frag.setDenominator(0);
    assertEquals(EXPECTED, frag.getDenominator());
  }

  @Test
  public void setDenominator_DenomintorIsValid() {
    final int EXPECTED = 5;
    Fragment frag = new Fragment();
    frag.setDenominator(5);
    assertEquals(EXPECTED, frag.getDenominator());
  }

  @Test
  public void print() {}

  @Test
  public void add_AddingZero() {
    final Fragment EXPECTED = new Fragment(2,3);
    Fragment frag1 = new Fragment(2,3);
    Fragment frag2 = new Fragment(0,1);
    assertEquals(EXPECTED,frag1.add(frag2));
  }
  @Test
  public void add_AddingSameFrag() {
    final Fragment EXPECTED = new Fragment(4,6);
  }

  @Test
  public void sub() {}

  @Test
  public void mult() {}

  @Test
  public void div() {}
}
