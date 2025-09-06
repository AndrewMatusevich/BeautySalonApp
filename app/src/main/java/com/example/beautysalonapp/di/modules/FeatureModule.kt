package com.example.beautysalonapp.di.modules

import com.example.beautysalon.features.auth_api.AuthFeatureApi
import com.example.beautysalon.features.booking_api.BookingFeatureApi
import com.example.beautysalon.features.booking_impl.api.BookingFeatureApiImpl
import com.example.beautysalon.features.client_api.api.ClientFeatureApi
import com.example.beautysalon.features.client_impl.api.ClientFeatureApiImpl
import com.example.beautysalon.features.admin_api.AdminFeatureApi
import com.example.beautysalon.features.admin_impl.api.AdminFeatureApiImpl
import com.example.beautysalon.features.master_impl.api.MasterFeatureApiImpl
import com.example.beautysalon.features.auth_impl.api.AuthFeatureApiImpl
import com.example.beautysalon.features.master_api.MasterFeatureApi
import dagger.Binds
import dagger.Module

@Module
interface FeatureModule {

   @Binds
   fun provideAuthFeature(authFeatureApiImpl: AuthFeatureApiImpl): AuthFeatureApi

   @Binds
   fun provideClientFeature(clientFeatureApiImpl: ClientFeatureApiImpl): ClientFeatureApi

   @Binds
   fun provideMasterFeature(masterFeatureApiImpl: MasterFeatureApiImpl): MasterFeatureApi

   @Binds
   fun provideAdminFeature(adminFeatureApiImpl: AdminFeatureApiImpl): AdminFeatureApi

   @Binds
   fun provideBookingFeature(bookingFeatureApiImpl: BookingFeatureApiImpl): BookingFeatureApi

  // @Binds
  // fun provideCalendarFeature(calendarFeatureApiImpl: CalendarFeatureApiImpl): CalendarFeatureApi

 //  @Binds
 //  fun provideProfileFeature(profileFeatureApiImpl: ProfileFeatureApiImpl): ProfileFeatureApi
}