package poker.version_text.model;

import java.util.ArrayList;

import poker.version_text.model.Card.Rank;

public enum HandType {
	HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;

	/**
	 * Determine the value of this hand. Note that this does not account for any
	 * tie-breaking
	 */
	public static HandType evaluateHand(ArrayList<Card> cards) {
		HandType currentEval = HighCard;

		if (isOnePair(cards))
			currentEval = OnePair;
		if (isTwoPair(cards))
			currentEval = TwoPair;
		if (isThreeOfAKind(cards))
			currentEval = ThreeOfAKind;
		if (isStraight(cards))
			currentEval = Straight;
		if (isFlush(cards))
			currentEval = Flush;
		if (isFullHouse(cards))
			currentEval = FullHouse;
		if (isFourOfAKind(cards))
			currentEval = FourOfAKind;
		if (isStraightFlush(cards))
			currentEval = StraightFlush;

		return currentEval;
	}

	public static boolean isOnePair(ArrayList<Card> cards) {
		boolean found = false;
		for (int i = 0; i < cards.size() - 1 && !found; i++) {
			for (int j = i + 1; j < cards.size() && !found; j++) {
				if (cards.get(i).getRank() == cards.get(j).getRank())
					found = true;
			}
		}
		return found;
	}

	public static boolean isTwoPair(ArrayList<Card> cards) {
		// Clone the cards, because we will be altering the list
		ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

		// Find the first pair; if found, remove the cards from the list
		boolean firstPairFound = false;
		for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
			for (int j = i + 1; j < clonedCards.size() && !firstPairFound; j++) {
				if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
					firstPairFound = true;
					clonedCards.remove(j); // Remove the later card
					clonedCards.remove(i); // Before the earlier one
				}
			}
		}
		// If a first pair was found, see if there is a second pair
		return firstPairFound && isOnePair(clonedCards);
	}

	public static boolean isThreeOfAKind(ArrayList<Card> cards) {
		boolean found = false;

		int[] ranks = new int[13];

		// [Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, J, Q, K, A]
		// [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if (card.getRank() == Rank.Ace) {
				ranks[12]++;
			} else if (card.getRank() == Rank.King) {
				ranks[11]++;
			} else if (card.getRank() == Rank.Queen) {
				ranks[10]++;
			} else if (card.getRank() == Rank.Jack) {
				ranks[9]++;
			} else {
				int position = Integer.parseInt(card.getRank().toString()) - 2;
				ranks[position]++;
			}
		}

		for (int i = 0; i < ranks.length; i++) {
			if (ranks[i] == 3) {
				found = true;
			}
		}

		return found;
	}

	public static boolean isStraight(ArrayList<Card> cards) {
		boolean found = false;

		cards.sort((x1, x2) -> x1.getRank().compareTo(x2.getRank()));

		int i = 0;
		while (i < 4 && cards.get(i).getRank().ordinal() + 1 == cards.get(i + 1).getRank().ordinal()) {
			i++;

		}

		if (i == 4)
			found = true;

		return found;
	}

	public static boolean isFlush(ArrayList<Card> cards) {
		boolean found = false;

		cards.sort((x1, x2) -> x1.getSuit().compareTo(x2.getSuit()));

		int i = 0;
		while (i < 4 && cards.get(i).getSuit() == cards.get(i + 1).getSuit()) {
			i++;

		}

		if (i == 4)
			found = true;

		return found;
	}

	public static boolean isFullHouse(ArrayList<Card> cards) {
		boolean found = false;

		int[] ranks = new int[13];

		// [Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, J, Q, K, A]
		// [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if (card.getRank() == Rank.Ace) {
				ranks[12]++;
			} else if (card.getRank() == Rank.King) {
				ranks[11]++;
			} else if (card.getRank() == Rank.Queen) {
				ranks[10]++;
			} else if (card.getRank() == Rank.Jack) {
				ranks[9]++;
			} else {
				int position = Integer.parseInt(card.getRank().toString()) - 2;
				ranks[position]++;
			}
		}
		for (int i = 0; i < ranks.length; i++)
			for (int j = 0; j < ranks.length; j++) {
				if (ranks[i] == 2 && ranks[j] == 3) {
					found = true;

				}
			}
		return found;

	}

	public static boolean isFourOfAKind(ArrayList<Card> cards) {
		boolean found = false;

		int[] ranks = new int[13];

		// [Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, J, Q, K, A]
		// [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if (card.getRank() == Rank.Ace) {
				ranks[12]++;
			} else if (card.getRank() == Rank.King) {
				ranks[11]++;
			} else if (card.getRank() == Rank.Queen) {
				ranks[10]++;
			} else if (card.getRank() == Rank.Jack) {
				ranks[9]++;
			} else {
				int position = Integer.parseInt(card.getRank().toString()) - 2;
				ranks[position]++;
			}
		}

		for (int i = 0; i < ranks.length; i++) {
			if (ranks[i] == 4) {
				found = true;
			}
		}

		return found;
	}

	public static boolean isStraightFlush(ArrayList<Card> cards) {
		boolean found = false;

		cards.sort((x1, x2) -> x1.getRank().compareTo(x2.getRank()));
		cards.sort((x1, x2) -> x1.getSuit().compareTo(x2.getSuit()));

		int i = 0;
		while (i < 4 && cards.get(i).getRank().ordinal() + 1 == cards.get(i + 1).getRank().ordinal()) {
			i++;

		}

		int j = 0;
		while (j < 4 && cards.get(i).getSuit() == cards.get(j + 1).getSuit()) {
			j++;

		}

		if (i == 4 && j == 4)
			found = true;

		return found;
	}
}
