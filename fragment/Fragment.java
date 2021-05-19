public class Fragment {

  private int numerator;
  private int denominator;

  public Fragment(int numerator, int denominator) {
    this.setDenominator(denominator);
    this.setNumerator(numerator);
  }

  public Fragment() {
    this.setDenominator(1);
    this.setNumerator(1);
  }

  public int getNumerator() {
    return this.numerator;
  }

  public void setNumerator(int numerator) {
    this.numerator = numerator;
  }

  public int getDenominator() {
    return this.denominator;
  }

  public void setDenominator(int denominator) {
    if (denominator != 0) {
      this.denominator = denominator;
    } else {
      this.denominator = 1;
      this.numerator = 1;
    }
  }

  public void print() {

    if (this.getNumerator() == 0 || this.getDenominator() == 1) {
      System.out.println("the fragment is : " + this.getNumerator());
    } else {

      if (!isPositive()) {
        System.out.println(
            "the fragment is : -" + this.getNumerator() + " / " + this.getDenominator());
      } else {
        System.out.println(
            "the fragment is : " + this.getNumerator() + " / " + this.getDenominator());
      }
    }
  }

  private boolean isPositive() {
    if ((this.getDenominator() < 0 && this.getNumerator() < 0)
        || (this.getDenominator() > 0 && this.getNumerator() > 0)) {
      return true;
    } else return false;
  }

  public Fragment add(Fragment otherFragment) {
    int commonDenominator = this.getDenominator() * otherFragment.getDenominator();
    int firstExpendedNumerator = this.getNumerator() * otherFragment.getDenominator();
    int secondExpendedNumerator = otherFragment.getNumerator() * this.getDenominator();
    Fragment newFragment =
        new Fragment(firstExpendedNumerator + secondExpendedNumerator, commonDenominator);
    newFragment.cutDown();
    return newFragment;
  }

  public Fragment sub(Fragment otherFragment) {
    int commonDenominator = this.getDenominator() * otherFragment.getDenominator();
    int firstExpendedNumerator = this.getNumerator() * otherFragment.getDenominator();
    int secondExpendedNumerator = otherFragment.getNumerator() * this.getDenominator();
    Fragment newFragment =
        new Fragment(firstExpendedNumerator - secondExpendedNumerator, commonDenominator);
    newFragment.cutDown();
    return newFragment;
  }

  public Fragment mult(Fragment otherFragment) {
    int multiplyNumerator = this.getNumerator() * otherFragment.getNumerator();
    int multiplyDenominator = this.getDenominator() * otherFragment.getDenominator();
    Fragment newFragment = new Fragment(multiplyNumerator, multiplyDenominator);
    newFragment.cutDown();
    return newFragment;
  }

  public Fragment div(Fragment otherFragment) {
    int divideNumerator = this.getNumerator() * otherFragment.getDenominator();
    int divideDenominator = this.getDenominator() * otherFragment.getNumerator();
    Fragment newFragment = new Fragment(divideNumerator, divideDenominator);
    newFragment.cutDown();
    return newFragment;
  }

  private void cutDown() {
    int item1 = Math.abs(this.getDenominator());
    int item2 = Math.abs(this.getNumerator());
    while (item1 != item2) {
      if (item1 > item2) {
        item1 = item1 - item2;
      } else {
        item2 = item2 - item1;
      }
    }
    this.setDenominator(this.getDenominator() / item2);
    this.setNumerator(this.getNumerator() / item1);
  }
}
