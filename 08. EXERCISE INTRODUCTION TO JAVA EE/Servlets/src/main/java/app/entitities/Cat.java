package app.entitities;

public class Cat {

	private String name;
	private String breed;
	private String color;
	private Integer numberOfLegs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getNumberOfLegs() {
		return numberOfLegs;
	}

	public void setNumberOfLegs(Integer numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", breed=" + breed + ", color=" + color + ", numberOfLegs=" + numberOfLegs + "]";
	}

	public static class Builder {
		private Cat cat;

		public Builder() {
			cat = new Cat();
		}

		public Builder name(String name) {
			cat.setName(name);
			return this;
		}

		public Builder breed(String breed) {
			cat.setBreed(breed);
			return this;
		}

		public Builder color(String color) {
			cat.setColor(color);
			return this;
		}

		public Builder numberOfLegs(Integer numberOfLegs) {
			cat.setNumberOfLegs(numberOfLegs);
			return this;
		}

		public Cat build() {
			return cat;
		}
	}
}
