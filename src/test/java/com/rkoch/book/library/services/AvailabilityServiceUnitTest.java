package com.rkoch.book.library.services;

import com.rkoch.book.library.services.definitions.AvailabilityServiceDefinition;
import com.rkoch.book.library.services.impl.AvailabilityService;
import com.rkoch.book.library.services.mocks.MockBookRepository;
import com.rkoch.book.library.services.mocks.TestData;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author rkoch
 */
public class AvailabilityServiceUnitTest {
    
    private final AvailabilityServiceDefinition service;
    
    {
        this.service = new AvailabilityService(new MockBookRepository());
    }

    @Test
    public void testBookAvailabilityForAvailableBook(){
        assertTrue(this.service.isBookAvailable(TestData.EXISTING_BOOK_ISBN));
    }
    
    @Test
    public void testBookAvailabilityForNotAvailableBook(){
        assertFalse(this.service.isBookAvailable(TestData.INEXISTING_BOOK_ISBN));
    }
    
}
