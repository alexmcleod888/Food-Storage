//food interface for must have calcExpiry method in food subclasses

package com.alexmcleod.food;

import java.util.Calendar;

public interface IFood {
    // imports todays date and exports true if this food item
    // has reached its expiry date, false otherwise.
    public boolean calcExpiry( Calendar today );
}
